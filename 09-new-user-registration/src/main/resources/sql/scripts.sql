
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('dinesh@gamil.com', '{noop}SpringUser@1234', 'user');
INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('mukesh@gamil.com', '{noop}SpringUser@2345', 'admin');
INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('vaibhav@gamil.com', '{noop}SpringUser@3456', 'user');
INSERT  INTO `customer` (`email`, `pwd`, `role`) VALUES ('shreyas@gamil.com', '{bcrypt}$2a$12$AT6srF6n03ybsIcghdJTqeQIVjpaGURHJTG8shG/ND52masW0PEaO', 'admin');