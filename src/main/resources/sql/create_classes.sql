CREATE TABLE IF NOT EXISTS`Classes`(
   `id` INTEGER
       NOT NULL
       PRIMARY KEY AUTOINCREMENT ,
   `teacher_id` INTEGER NOT NULL,
   `subject_code` VARCHAR(255) NOT NULL,
     FOREIGN KEY(`teacher_id`) REFERENCES "Teachers"("id"),
     FOREIGN KEY(`subject_code`) REFERENCES "Subjects"("code")
);