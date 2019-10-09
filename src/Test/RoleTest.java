import com.revanwang.wms.domain.Role;
import com.revanwang.wms.service.IRoleService;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleTest {

    @Autowired
    private IRoleService roleService;

    @Test
    public void saveTest() {
        Role role = new Role();
        role.setName("系统管理员");
        role.setSn("SYSTEM_ADMIN");

        this.roleService.save(role);
    }

}
