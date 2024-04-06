@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setProductId(orderDTO.getProductId());
        order.setQuantity(orderDTO.getQuantity());
        orderRepository.save(order);
    }

    public OrderDTO getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setProductId(order.getProductId());
        orderDTO.setQuantity(order.getQuantity());
        return orderDTO;
    }
}
