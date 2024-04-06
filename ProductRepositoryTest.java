@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testConcurrentUpdates() {
        // Create a product
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(10.0);
        productRepository.save(product);

        // Retrieve the product in two separate transactions
        Product product1 = productRepository.findById(product.getId()).get();
        Product product2 = productRepository.findById(product.getId()).get();

        // Modify the products concurrently
        product1.setPrice(15.0);
        product2.setPrice(20.0);

        // Save the products
        productRepository.save(product1);
        productRepository.save(product2);

        // Ensure that a concurrency exception is thrown
        assertThatThrownBy(() -> productRepository.flush())
                .isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
}
