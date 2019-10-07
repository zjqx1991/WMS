import com.revanwang.wms.domain.Department;
import com.revanwang.wms.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void saveTest() {
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setName("市场部");
            department.setSn("market");
            this.departmentService.save(department);
        }
    }


    @Test
    public void deleteTest() {
        this.departmentService.delete(1L);
    }

    @Test
    public void updateTest() {
        Department department = new Department();
        department.setId(3L);
        department.setName("财务部");
        department.setSn("Accounting");
        this.departmentService.update(department);
    }

    @Test
    public void getDepartTest() {
        Department department = this.departmentService.get(2L);
        System.out.println("DepartmentTest.getTest：" + department);
    }

    @Test
    public void getListTest() {
        List<Department> list = this.departmentService.getList();
        for (Department depart : list) {
            System.out.println("DepartmentTest.getListTest：" + depart);
        }
    }

}
