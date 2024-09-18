CREATE TABLE IF NOT EXISTS `Grades`(
     `student_id` INTEGER
         NOT NULL,
     `subject_code` VARCHAR(255)
         NOT NULL,
     `grade` INTEGER
         NOT NULL
         DEFAULT '0',
     `class_id` INT
         NOT NULL,
     PRIMARY KEY(student_id, subject_code),
     FOREIGN KEY("student_id") REFERENCES "Students"("id"),
     FOREIGN KEY("class_id") REFERENCES "Classes"("id"),
     FOREIGN KEY("subject_code") REFERENCES "Subjects"("code")
);