import java.time.LocalDate

data class Task(
    val description: String,
    val priority: Priority,
    val dueDate: LocalDate,
    val category: String,
    var isCompleted: Boolean = false
)

enum class Priority {
    HIGH, MEDIUM, LOW
}

interface TaskOperation {
    fun addTask(task: Task)
    fun viewTasks()
    fun viewTasksByPriority(priority: Priority)
    fun viewTasksByCategory(category: String)
    fun removeTask(description: String)
    fun markTaskAsCompleted(description: String)
}

class AdvancedTaskManager : TaskOperation {
    private val tasks = mutableListOf<Task>()

    override fun addTask(task: Task) {
        tasks.add(task)
        println("Task added: $task")
    }

    override fun viewTasks() {
        if (tasks.isEmpty()) {
            println("No tasks available.")
        } else {
            println("Tasks:")
            tasks.forEach { println("- $it") }
        }
    }

    override fun viewTasksByPriority(priority: Priority) {
        val filteredTasks = tasks.filter { it.priority == priority }
        if (filteredTasks.isEmpty()) {
            println("No tasks with priority $priority.")
        } else {
            println("Tasks with priority $priority:")
            filteredTasks.forEach { println("- $it") }
        }
    }

    override fun viewTasksByCategory(category: String) {
        val filteredTasks = tasks.filter { it.category == category }
        if (filteredTasks.isEmpty()) {
            println("No tasks in category '$category'.")
        } else {
            println("Tasks in category '$category':")
            filteredTasks.forEach { println("- $it") }
        }
    }

    override fun removeTask(description: String) {
        val taskToRemove = tasks.find { it.description == description }
        if (taskToRemove != null) {
            tasks.remove(taskToRemove)
            println("Task removed: $taskToRemove")
        } else {
            println("Task with description '$description' not found.")
        }
    }

    override fun markTaskAsCompleted(description: String) {
        val taskToMark = tasks.find { it.description == description }
        if (taskToMark != null) {
            taskToMark.isCompleted = true
            println("Task marked as completed: $taskToMark")
        } else {
            println("Task with description '$description' not found.")
        }
    }
}

fun main() {
    val advancedTaskManager = AdvancedTaskManager()

    advancedTaskManager.addTask(Task("Implement feature X", Priority.HIGH, LocalDate.now().plusDays(7), "Development"))
    advancedTaskManager.addTask(Task("Refactor codebase", Priority.MEDIUM, LocalDate.now().plusDays(14), "Maintenance"))
    advancedTaskManager.addTask(Task("Write unit tests", Priority.HIGH, LocalDate.now().plusDays(5), "Testing"))

    println("All Tasks:")
    advancedTaskManager.viewTasks()

    println("\nHigh Priority Tasks:")
    advancedTaskManager.viewTasksByPriority(Priority.HIGH)

    println("\nDevelopment Tasks:")
    advancedTaskManager.viewTasksByCategory("Development")

    advancedTaskManager.markTaskAsCompleted("Write unit tests")

    println("\nRemaining Tasks:")
    advancedTaskManager.viewTasks()
}
