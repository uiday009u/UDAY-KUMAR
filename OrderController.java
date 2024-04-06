@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        // Set the user ID from the authenticated user's token in the orderDTO
        orderService.placeOrder(orderDTO);
        return ResponseEntity.ok("Order placed successfully");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        // Add authorization logic to check if user is authorized to view the order
        OrderDTO orderDTO = orderService.getOrder(orderId);
        return ResponseEntity.ok(orderDTO);
    }
}
