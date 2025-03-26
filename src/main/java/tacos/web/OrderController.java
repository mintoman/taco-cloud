package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.jpa.OrderRepository;
import tacos.domain.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model
                            //, @AuthenticationPrincipal User user
    ) {
        Order order = (Order) model.getAttribute("order");
        if (order == null || order.getTacos().isEmpty()) {
            return "redirect:/design";
        }
        //order.setUser(user);
//        order.setStreet(user.getStreet());
//        order.setCity(user.getCity());
//        order.setState(user.getState());
//        order.setZip(user.getZip());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        orderRepo.save(order);
        log.info("Processing order: {}",order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
