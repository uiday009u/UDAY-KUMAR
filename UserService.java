@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
    }

    public String loginUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null || !user.getPassword().equals(userDTO.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwtTokenProvider.generateToken(user.getUsername());
    }
}
