package taskbackend.controller;

import br.ce.wcaquino.taskbackend.controller.TaskController;
import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

public class TaskControllerTest {

    @InjectMocks
    private TaskController controller;
    @Mock
    private TaskRepo taskRepo;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao(){
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        try {
                controller.save(todo);
                fail("Não deveria ter chegado nesse ponto");
            } catch (ValidationException e) {
                assertEquals("Fill the task description", e.getMessage());
            }
    }

    @Test
    public void naoDeveSalvarTarefaSemData(){
        Task todo = new Task();
        todo.setTask("Descricao");
        try {
            controller.save(todo);
            fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada(){
        Task todo = new Task();
        todo.setTask("Descricao");
        todo.setDueDate(LocalDate.of(2010,01,01));
        try {
            controller.save(todo);
            fail("Não deveria ter chegado nesse ponto");
        } catch (ValidationException e) {
            assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws ValidationException {
        Task todo = new Task();
        todo.setTask("Descricao");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);
        verify(taskRepo).save(todo);
    }
}
