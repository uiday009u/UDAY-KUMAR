@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productRepository.save(product);
    }

    @Transactional
    public ProductDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Updating product fields
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        // Saving the updated product
        productRepository.save(product);
    }
}
