CREATE TABLE `wsuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_actif` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firtsname` varchar(255) DEFAULT NULL,
  `is_admin` int(11) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `pasword` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

CREATE TABLE `contact_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fuser` varchar(255) DEFAULT NULL,
  `message` longtext,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;