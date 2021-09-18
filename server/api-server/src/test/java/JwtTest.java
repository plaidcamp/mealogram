import com.plaidcamp.mealogram.common.constants.AuthConstant;
import com.plaidcamp.mealogram.common.provider.FuncUtilProvider;
import com.plaidcamp.mealogram.common.provider.JwtTokenProvider;
import com.plaidcamp.mealogram.domain.user.UserMaster;
import com.plaidcamp.mealogram.domain.user.UserMasterRepository;
import com.plaidcamp.mealogram.user.service.CustomUserDetailService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JwtTest {
    private static FuncUtilProvider funcUtil = new FuncUtilProvider();
    private static UserMasterRepository repo = new UserMasterRepository() {
        @Override
        public Optional<UserMaster> findByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public List<UserMaster> findAll() {
            return null;
        }

        @Override
        public List<UserMaster> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<UserMaster> findAllById(Iterable<UUID> uuids) {
            return null;
        }

        @Override
        public <S extends UserMaster> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends UserMaster> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public void deleteInBatch(Iterable<UserMaster> entities) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public UserMaster getOne(UUID uuid) {
            return null;
        }

        @Override
        public <S extends UserMaster> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends UserMaster> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<UserMaster> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends UserMaster> S save(S entity) {
            return null;
        }

        @Override
        public Optional<UserMaster> findById(UUID uuid) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(UUID uuid) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(UUID uuid) {

        }

        @Override
        public void delete(UserMaster entity) {

        }

        @Override
        public void deleteAll(Iterable<? extends UserMaster> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends UserMaster> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends UserMaster> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends UserMaster> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends UserMaster> boolean exists(Example<S> example) {
            return false;
        }
    };

    public static void main(String[] args) {
        JwtTest test = new JwtTest();
        try {
            test.start();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void start() throws IOException {

        // create
        JwtTokenProvider provider = new JwtTokenProvider(repo, funcUtil);
        provider.init();
        UserMaster user = createUser();
        String accessToken = provider.createAccessToken(user);
        String refreshToken = provider.createRefreshToken(user);

        System.out.println("access : " + accessToken);
        System.out.println("refresh : " + refreshToken);

        // validate
        System.out.println("access : " + provider.validateToken(AuthConstant.ACCESS_TOKEN, accessToken));
        System.out.println("refresh : " + provider.validateToken(AuthConstant.REFRESH_TOKEN, refreshToken));

        // decode

    }

    private UserMaster createUser() {

        return UserMaster.builder()
                .email("plaidcamp@email.com")
                .phone("010-1234-1234")
                .password("plaidcamp123")
                .administrate(AuthConstant.ROLE_USER)
                .build();
    }
}
