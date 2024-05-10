package com.timi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.timi.dao.CourseDAO;
import com.timi.dao.DatabaseConnection;
import com.timi.dao.UserDAO;
import com.timi.exception.DAOException;
import com.timi.exception.EmailAlreadyExistsException;
import com.timi.exception.InvalidEmailException;
import com.timi.model.Admin;
import com.timi.model.Course;
import com.timi.model.Instructor;
import com.timi.model.Level;
import com.timi.model.Student;
import com.timi.model.User;

public class UserDAOImpl implements UserDAO {
    private DatabaseConnection dbConnection;
    private CourseDAO courseDAO;

    public UserDAOImpl() {
        dbConnection = DatabaseConnection.getInstance();
        courseDAO = new CourseDAOImpl();
    }

    @Override
    public void addUser(User user) {
        Connection connection = dbConnection.getConnection();
        try {

            if (emailExists(user.getEmail())) {
                System.out.println("Failed to add user!");
                throw new EmailAlreadyExistsException("\nEmail already exists: " + user.getEmail());
            }

            // check if user has id, if it has id, we should add the id in the query as well
            PreparedStatement ps = null;
            if (user.getId() != 0) {
                ps = connection.prepareStatement("INSERT INTO Users (id, email, username, password, role, level, points, department, dateOfEmployment, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, user.getId());
            } else {
                ps = connection.prepareStatement("INSERT INTO Users (email, username, password, role, level, points, department, dateOfEmployment, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            }
            int cnt = (user.getId() != 0) ? 1 : 0;
            ps.setString(1 + cnt, user.getEmail());
            ps.setString(2 + cnt, user.getUsername());
            ps.setString(3 + cnt, user.getPassword());
            ps.setString(4 + cnt, user.getRole().toString());

            // Set parameters based on user role
            if (user instanceof Student) {
                Student student = (Student) user;
                ps.setString(5 + cnt, student.getLevel().toString());
                ps.setInt(6 + cnt, student.getPoints());
                ps.setNull(7 + cnt, Types.VARCHAR);  // department
                ps.setNull(8 + cnt, Types.DATE);     // dateOfEmplyment
                ps.setNull(9 + cnt, Types.VARCHAR); // telephone
                if(!student.validateEmail(student.getEmail()))
                {
                    System.out.println("Email is not valid!");
                    throw new InvalidEmailException("Email is not valid: " + student.getEmail());
                }
            } else if (user instanceof Instructor) {
                Instructor instructor = (Instructor) user;
                ps.setNull(5 + cnt, Types.VARCHAR);  // level
                ps.setNull(6 + cnt, Types.INTEGER);  // points
                ps.setString(7 + cnt, instructor.getDepartment());
                ps.setDate(8 + cnt, new java.sql.Date(instructor.getDateOfEmployment().getTime()));
                ps.setNull(9 + cnt, Types.VARCHAR); // telephone
                if(!instructor.validateEmail(instructor.getEmail()))
                {
                    System.out.println("Email is not valid for an instructor!");
                    throw new InvalidEmailException("Email is not valid for an instructor: " + instructor.getEmail() +"\nIt should be under @unibuc.ro domain!");
                }
            } else if (user instanceof Admin) {
                Admin admin = (Admin) user;
                ps.setNull(5 + cnt, Types.VARCHAR);  // level
                ps.setNull(6 + cnt, Types.INTEGER);  // points
                ps.setNull(7 + cnt, Types.VARCHAR);  // department
                ps.setNull(8 + cnt, Types.DATE);     // dateOfEmplyment
                ps.setString(9 + cnt, admin.getTelephone());
                if(!admin.validateEmail(admin.getEmail()))
                {
                    System.out.println("Email is not valid for an admin!");
                    throw new InvalidEmailException("Email is not valid for an admin: " + admin.getEmail());
                }
            }

            ps.executeUpdate();
            ps.close();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add user!");
        } catch (InvalidEmailException e) {
            e.printStackTrace();
            System.out.println("Failed to add user: " + e.getMessage());
        }
    }

    private boolean emailExists(String email) throws SQLException {
        Connection connection = dbConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
        return false;
    }

    @Override
   public User getUserById(int userId) {
        Connection connection = dbConnection.getConnection();
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                // Create user object (Student, Instructor, Admin) based on role
                if (role.equals("STUDENT")) {
                    int points = resultSet.getInt("points");
                    String level = resultSet.getString("level");
                    user = new Student(userId, email, username, password, Level.valueOf(level), points);
                } else if (role.equals("INSTRUCTOR")) {
                    String department = resultSet.getString("department");
                    Date dateOfEmployment = resultSet.getDate("dateOfEmployment");
                    user = new Instructor(userId, email, username, password, dateOfEmployment, department);
                } else if (role.equals("ADMIN")) {
                    String telephone = resultSet.getString("telephone");
                    user = new Admin(userId, email, username, password, telephone);
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

   @Override
public List<User> getAllUsers() throws DAOException {
    Connection connection = dbConnection.getConnection();
    List<User> users = new ArrayList<>();

    try {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");

            // Create user object (Student, Instructor, Admin) based on role
            if (role.equals("STUDENT")) {
                int points = resultSet.getInt("points");
                String level = resultSet.getString("level");
                Student student = new Student(userId, email, username, password, Level.valueOf(level), points);
                List<Course> enrolledCourses = courseDAO.getUserCourses(userId);
                student.setEnrolledCourses(enrolledCourses);
                users.add(student);
            } else if (role.equals("INSTRUCTOR")) {
                String department = resultSet.getString("department");
                Date dateOfEmployment = resultSet.getDate("dateOfEmployment");
                Instructor instructor = new Instructor(userId, email, username, password, dateOfEmployment, department);
                List<Course> teachingCourses = courseDAO.getUserCourses(userId); 
                instructor.setTeachingCourses(teachingCourses);
                users.add(instructor);
            } else if (role.equals("ADMIN")) {
                String telephone = resultSet.getString("telephone");
                users.add(new Admin(userId, email, username, password, telephone));
            }
        }

        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return users;
}

    @Override
    public void updateUser(User user) throws InvalidEmailException {
        Connection connection = dbConnection.getConnection();
        try {
            if (emailExists(user.getEmail())) {
                System.out.println("Failed to update user!");
                throw new EmailAlreadyExistsException("\nEmail already exists: " + user.getEmail());
            }

            PreparedStatement ps = connection.prepareStatement("UPDATE Users SET email = ?, username = ?, password = ?, role = ?, level = ?, points = ?, department = ?, dateOfEmployment = ?, telephone = ? WHERE id = ?");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().toString());

            // Set parameters based on user role
            if (user instanceof Student) {
                Student student = (Student) user;
                ps.setString(5, student.getLevel().toString());
                ps.setInt(6, student.getPoints());
                ps.setNull(7, Types.VARCHAR);  // department
                ps.setNull(8, Types.DATE);     // dateOfEmplyment
                ps.setNull(9, Types.VARCHAR); // telephone
                if(!student.validateEmail(student.getEmail()))
                {
                    System.out.println("Email is not valid!");
                    throw new InvalidEmailException("Email is not valid: " + student.getEmail());
                }
            } else if (user instanceof Instructor) {
                Instructor instructor = (Instructor) user;
                ps.setNull(5, Types.VARCHAR);  // level
                ps.setNull(6, Types.INTEGER);  // points
                ps.setString(7, instructor.getDepartment());
                ps.setDate(8, new java.sql.Date(instructor.getDateOfEmployment().getTime()));
                ps.setNull(9, Types.VARCHAR); // telephone
                if(!instructor.validateEmail(instructor.getEmail()))
                {
                    System.out.println("Email is not valid for an instructor!");
                    throw new InvalidEmailException("Email is not valid for an instructor: " + instructor.getEmail() +"\nIt should be under @unibuc.ro domain!");
                }
            } else if (user instanceof Admin) {
                Admin admin = (Admin) user;
                ps.setNull(5, Types.VARCHAR);  // level
                ps.setNull(6, Types.INTEGER);  // points
                ps.setNull(7, Types.VARCHAR);  // department
                ps.setNull(8, Types.DATE);     // dateOfEmplyment
                ps.setString(9, admin.getTelephone());
                if(!admin.validateEmail(admin.getEmail()))
                {
                    System.out.println("Email is not valid for an admin!");
                    throw new InvalidEmailException("Email is not valid for an admin: " + admin.getEmail());
                }
            }
            ps.setInt(10, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteUser(int userId) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void addCourseToUser(int userId, int courseId) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO UserCourses (userId, courseId) VALUES (?, ?)");
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


