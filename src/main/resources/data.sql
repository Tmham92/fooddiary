<<<<<<< HEAD
INSERT INTO genes (genbank_id, abbreviation, name, process) VALUES
('MN:123245', 'psi_OPS', 'Operator psi interaction', 'mental health'),
('AB"23412', 'FU-BAR', 'F.U. beyond all recognition', 'mental health'),
('NA:737373', 'prnETR', 'Goes to the toilet often', 'metabolism'),
('NA:636254', 'dstrphy', 'Induces muscular dystrophy through inuse', 'physiology'),
('NA:755656', 'hngr', 'Hungry already before lunch', 'metabolism');

#henkie en zwanie als passwords

INSERT INTO users (authority, first_name, last_name, email, password, enabled) VALUES
('ADMIN', 'Henk', 'Brugman', 'h.brugman@example.com', '$2a$10$ZjbddtiSvv0y/nJSoPj3aOtXvluja2iB0wszIG4hsD/aOoiFV.E8G', 1);

INSERT INTO users (authority, first_name, last_name, email, password, enabled) VALUES
('USER', 'Jantien', 'Zwaan', 'k.minogue@example.com', '$2a$10$qIJ0O316fWNlZ.C3w.the.7I.GzRdvjVQe.Oz8Yiga1YVB/a37FyW', 1);
=======
#guest user en als passwords

INSERT INTO user (id, user_code, password, role, enabled) VALUES
(1, 'guest', '$2a$10$dOXqGz8EqR6OsMVVR6aWGOufl1PZZg3jZSW2/PmGXi6A4kFbeMbA6', 'USER', 1);

>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
