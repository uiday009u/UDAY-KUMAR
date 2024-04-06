@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        // Mocking userDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");

        // Mocking userRepository
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Testing registerUser method
        userService.registerUser(userDTO);

        // Verify that userRepository.save() is called
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testLoginUser() {
        // Mocking userDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");

        // Mocking userRepository
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(user);

        // Mocking jwtTokenProvider
        Mockito.when(jwtTokenProvider.generateToken("testUser")).thenReturn("mockedToken");

        // Testing loginUser method
        String token = userService.loginUser(userDTO);

        // Verify that jwtTokenProvider.generateToken() is called
        Mockito.verify(jwtTokenProvider, Mockito.times(1)).generateToken("testUser");

        // Verify that the returned token is not null
        assertNotNull(token);
    }
}
