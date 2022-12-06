```mermaid
erDiagram
    HAIRSTYLIST ||--o{ APPOINTMENT : schedules
    HAIRSTYLIST {
        string m-firstName
        string m-lastName
        string m-address
        string m-phoneNumber
        string m-socialSecurityNumber
        float m-salary
    }
    CLIENT ||--|{ APPOINTMENT : schedules
    CLIENT {
        string m-firstName
        string o-lastName
        string m-phoneNumber
    }

}
```





