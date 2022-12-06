```mermaid
graph TD;
   A-->B;
   A-->C;
   B-->D;

```
```mermaid
classDiagram
   A <|-- B
   A : attribute1
   A : attribute2
   A : method1()
   B : attribute3
   B : attribute4
   B : method2()

```

```mermaid
sequenceDiagram
   A->>B: Message 1
   B->>A: Message 2
   A->>B: Message 3
   B->>A: Message 4
```

```mermaid
gantt
   dateFormat  YYYY-MM-DD
   title Adding GANTT diagram functionality to mermaid

   section A section
   Completed task            :done,    des1, 2014-01-06,2014-01-08
   Active task               :active,  des2, 2014-01-09, 3d
   Future task               :         des3, after des2, 5d
   Future task2               :         des4, after des3, 5d

   section Critical tasks
   Completed task in the critical line :crit, done, 2014-01-06,24h
   Implement parser and jison          :crit, done, after des1, 2d
   Create tests for parser             :crit, active, 3d
   Future task in critical line        :crit, 5d
   Create tests for renderer           :2d
   Add to mermaid                      :1d

```





