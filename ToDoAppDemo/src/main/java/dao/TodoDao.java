package dao;
import java.sql.SQLException;
import java.util.List;

import model.Todo;

public interface TodoDao {

        void insertTodo(Todo todo) throws SQLException;

        Todo selectTodo(long todoId);

        List<Todo> selectAllTodos();

        boolean deleteTodo(long id) throws SQLException;

        void updateTodo(Todo todo) throws SQLException;


}
