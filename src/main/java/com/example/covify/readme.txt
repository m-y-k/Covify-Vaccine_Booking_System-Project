

    This is covid vaccine booking management system project for backend system design which is built using tech stacks like
    java, springboot, jpa, hibernate orm, mysql.

    There are many APIs written which performs CRUD operations over all the entities. These entities are -
        1. User
        2. Vaccination Center
        3. Doctor
        4. Appointment

    For security reasons, Request DTOs and ResponseDTOs are formed here which restricts
    our data from exposing in front of the User.

    Various exceptions that can occur while performing CRUD operations are also handled carefully.

    This project has an email sending functionality which sends mail to the User whenever an Appointment is booked.
    Here, a user can book vaccine of his choice among given 3 vaccines, and he can only book Dose 2 if he has taken
        Dose 1 before, otherwise, the appointment will not be booked.

     ** You just need to clone this project in your IDE and then, you can test its APIs **