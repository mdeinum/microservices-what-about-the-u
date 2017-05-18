package nl.conspect.yos.yosorderservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by marten on 16-05-17.
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", orderRepository.findAllSummarizedBy());
        return "orders";
    }

    @GetMapping(params = {"customerId"})
    public String listForCustomer(@RequestParam long customerId, Model model) {
        model.addAttribute("orders", orderRepository.findAllSummarizedByCustomerId(customerId));
        return "orders";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Order order, Model model) {
        model.addAttribute("order", order);
        return "order";
    }


}
