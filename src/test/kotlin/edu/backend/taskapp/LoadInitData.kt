/*package edu.backend.taskapp

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(
    statements = [
        "DELETE FROM public.reminder",
        "DELETE FROM public.task",
        "DELETE FROM public.priority",
        "DELETE FROM public.status",
        "DELETE FROM public.users"
    ],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Sql(
    scripts = ["/import-users.sql", "/import-priorities.sql", "/import-status.sql", "/import-tasks.sql", "/import-reminders.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class LoadInitData(
    @Autowired
    val taskRepository: TaskRepository,
) {

    @Test
    fun testTaskFindAll() {
        val taskList: List<Task> = taskRepository.findAll()
        Assertions.assertTrue(taskList.size == 2)
    }

    @Test
    fun testTaskFindById() {
        val task: Task = taskRepository.findById(1).get()
        Assertions.assertTrue(task.id?.toInt() == 1)
    }

}*/