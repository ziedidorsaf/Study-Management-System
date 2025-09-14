# Study Management System

The Study Management System is a web-based application that centralizes academic administration. **Currently, all system functionalities are managed exclusively by the admin user.** 

## Features

- **Admin Dashboard:** Central interface for managing the academic system.
- **Student Management:** Add, update, and remove student records.
- **Exam Management:** Create and schedule exams.
- **Session Management:** Organize academic sessions and timelines.
- **Speciality Management:** Define and manage academic specialities or departments.
- **Grade Management:** Enter, update, and track student grades.

## Project Structure

```
.
├── .Settings/               # IDE or project-specific settings
├── .classpath               # Java classpath configuration
├── .project                 # Eclipse project configuration
├── .tern-project            # TernJS project configuration
├── README.md                # Project documentation
└── src/
    ├── main/
    │   ├── java/            # Java backend source code
    │   └── webapp/          # Frontend web application (JSP, HTML, CSS, JS)
```

## Technologies Used

- **Backend:** Java
- **Frontend:** JSP, HTML, CSS, JavaScript 
- **Database:** MySQL

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ziedidorsaf/Study-Management-System.git
   cd Study-Management-System
   ```

2. **Install dependencies** as required by your backend and frontend stack.

3. **Configure MySQL database** and relevant environment variables as needed.

4. **Run the application** using your preferred IDE or build tool.

## Roadmap

Planned enhancements include:
- Introducing user roles for students and teachers
- Role-based access control and permissions
- User self-service portals
- Enhanced notifications and reporting

## License

MIT License
