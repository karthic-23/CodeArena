-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: codearena
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `problems`
--

DROP TABLE IF EXISTS `problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text,
  `difficulty` varchar(255) DEFAULT NULL,
  `hidden_input` text,
  `hidden_output` text,
  `input_example` text,
  `output_example` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problems`
--

LOCK TABLES `problems` WRITE;
/*!40000 ALTER TABLE `problems` DISABLE KEYS */;
INSERT INTO `problems` VALUES (1,'Return sum of two integers.','EASY','1 2;3 4;5 6;7 8;9 10;11 12;13 14;15 16','3;7;11;15;19;23;27;31','2 3;10 20','5;30','Add Two Numbers'),(2,'Return difference of two integers.','EASY','5 3;10 5;20 10;50 25;9 3;7 2;6 1;100 50','2;5;10;25;6;5;5;50','5 3;20 10','2;10','Subtract Two Numbers'),(3,'Return product of two integers.','EASY','2 3;4 5;6 7;8 9;2 10;4 6;7 8;9 9','6;20;42;72;20;24;56;81','2 3;4 5','6;20','Multiply Two Numbers'),(4,'Return integer division.','EASY','10 2;9 3;20 4;15 5;8 2;100 10;50 5;30 3','5;3;5;3;4;10;10;10','10 2;9 3','5;3','Divide Two Numbers'),(5,'Check if number is even or odd.','EASY','2;3;10;15;22;27;100;101','even;odd;even;odd;even;odd;even;odd','2;7','even;odd','Even or Odd'),(6,'Find maximum of two numbers.','EASY','2 3;10 5;6 8;7 7;100 50;22 33;44 11;9 3','3;10;8;7;100;33;44;9','2 3;10 5','3;10','Maximum of Two'),(7,'Find minimum of two numbers.','EASY','2 3;10 5;6 8;7 7;100 50;22 33;44 11;9 3','2;5;6;7;50;22;11;3','2 3;10 5','2;5','Minimum of Two'),(8,'Return square of number.','EASY','2;5;10;7;8;9;11;12','4;25;100;49;64;81;121;144','2;5','4;25','Square Number'),(9,'Return cube of number.','EASY','2;3;4;5;6;7;8;9','8;27;64;125;216;343;512;729','2;3','8;27','Cube Number'),(10,'Return a^b.','EASY','2 3;3 2;4 2;5 2;6 1;7 2;8 2;9 2','8;9;16;25;6;49;64;81','2 3;3 2','8;9','Power'),(11,'Find sum of array.','EASY','3\n1 2 3;4\n4 5 6 7;5\n1 1 1 1 1;2\n10 20;3\n2 2 2;6\n1 1 1 1 1 1;3\n9 9 9;2\n7 8','6;22;5;30;6;6;27;15','3\n1 2 3;4\n4 5 6 7','6;22','Sum of Array'),(12,'Find minimum element.','EASY','3\n1 2 3;4\n4 5 6 7;5\n9 8 7 6 5;2\n100 200;3\n5 5 5;6\n1 2 3 4 5 6;3\n9 2 3;2\n7 8','1;4;5;100;5;1;2;7','3\n1 2 3;4\n4 5 6 7','1;4','Minimum in Array'),(13,'Find maximum element.','EASY','3\n1 2 3;4\n4 5 6 7;5\n9 8 7 6 5;2\n100 200;3\n5 5 5;6\n1 2 3 4 5 6;3\n9 2 3;2\n7 8','3;7;9;200;5;6;9;8','3\n1 2 3;4\n4 5 6 7','3;7','Maximum in Array'),(14,'Count elements in array.','EASY','3\n1 2 3;5\n1 2 3 4 5;4\n4 4 4 4;2\n10 20;3\n2 2 2;6\n1 1 1 1 1 1;3\n9 9 9;2\n7 8','3;5;4;2;3;6;3;2','3\n1 2 3;5\n1 2 3 4 5','3;5','Count Elements'),(15,'Reverse the array.','EASY','3\n1 2 3;4\n4 5 6 7;2\n5 6;5\n1 1 1 1 1;3\n3 2 1;4\n8 7 6 5;2\n10 20;3\n4 3 2','3 2 1;7 6 5 4;6 5;1 1 1 1 1;1 2 3;5 6 7 8;20 10;2 3 4','3\n1 2 3;4\n4 5 6 7','3 2 1;7 6 5 4','Reverse Array'),(16,'Check if element exists.','EASY','3\n1 2 3\n2;4\n4 5 6 7\n8;3\n2 4 6\n6;5\n1 2 3 4 5\n3;2\n10 20\n20;3\n5 5 5\n5;4\n7 8 9 10\n11;3\n1 1 1\n2','true;false;true;true;true;true;false;false','3\n1 2 3\n2;4\n4 5 6 7\n8','true;false','Search Element'),(17,'Sum only even numbers.','EASY','3\n1 2 3;4\n4 5 6 7;5\n2 4 6 8 10;3\n1 3 5;6\n1 2 3 4 5 6;2\n7 9;3\n2 2 2;4\n1 1 1 1','2;10;30;9;12;0;6;0','3\n1 2 3;4\n4 5 6 7','2;10','Sum Even Numbers'),(18,'Sum only odd numbers.','EASY','3\n1 2 3;4\n4 5 6 7;5\n2 4 6 8 10;3\n1 3 5;6\n1 2 3 4 5 6;2\n7 9;3\n2 2 2;4\n1 1 1 1','4;12;0;9;9;16;6;4','3\n1 2 3;4\n4 5 6 7','4;12','Sum Odd Numbers'),(19,'Return first element.','EASY','3\n1 2 3;4\n4 5 6 7;5\n9 8 7 6 5;2\n100 200;3\n5 5 5;6\n1 2 3 4 5 6;3\n9 2 3;2\n7 8','1;4;9;100;5;1;9;7','3\n1 2 3;4\n4 5 6 7','1;4','First Element'),(20,'Return last element.','EASY','3\n1 2 3;4\n4 5 6 7;5\n9 8 7 6 5;2\n100 200;3\n5 5 5;6\n1 2 3 4 5 6;3\n9 2 3;2\n7 8','3;7;5;200;5;6;3;8','3\n1 2 3;4\n4 5 6 7','3;7','Last Element'),(21,'Reverse given string.','EASY','hello;world;java;code;test;abc;xyz;openai','olleh;dlrow;avaj;edoc;tset;cba;zyx;ianepo','hello;code','olleh;edoc','Reverse String'),(22,'Find length of string.','EASY','hello;world;java;code;test;abc;xyz;openai','5;5;4;4;4;3;3;6','hello;world','5;5','String Length'),(23,'Check if string is palindrome.','EASY','madam;racecar;hello;test;level;noon;abc;xyz','true;true;false;false;true;true;false;false','madam;hello','true;false','Palindrome String'),(24,'Convert string to uppercase.','EASY','hello;code;java;test;abc;xyz;openai;chat','HELLO;CODE;JAVA;TEST;ABC;XYZ;OPENAI;CHAT','hello;code','HELLO;CODE','Uppercase String'),(25,'Convert string to lowercase.','EASY','HELLO;CODE;JAVA;TEST;ABC;XYZ;OPENAI;CHAT','hello;code;java;test;abc;xyz;openai;chat','HELLO;CODE','hello;code','Lowercase String'),(26,'Count vowels in string.','EASY','hello;aeiou;xyz;test;programming;data;science;ai','2;5;0;1;3;2;3;2','hello;aeiou','2;5','Count Vowels'),(27,'Remove spaces from string.','EASY','h e l l o;co de;ja va;te st;a b c;x y z;op en ai;ch at','hello;code;java;test;abc;xyz;openai;chat','h e l l o;co de','hello;code','Remove Spaces'),(28,'Join two strings.','EASY','hello world;code arena;java script;data base;ai model;chat gpt;open ai;machine learning','helloworld;codearena;javascript;database;aimodel;chatgpt;openai;machinelearning','hello world;code arena','helloworld;codearena','Concatenate Strings'),(29,'Return first character.','EASY','hello;code;java;test;abc;xyz;openai;chat','h;c;j;t;a;x;o;c','hello;code','h;c','First Character'),(30,'Return last character.','EASY','hello;code;java;test;abc;xyz;openai;chat','o;e;a;t;c;z;i;t','hello;code','o;e','Last Character'),(31,'Find factorial of number.','MEDIUM','5;6;7;8;3;4;2;1','120;720;5040;40320;6;24;2;1','5;3','120;6','Factorial'),(32,'Print nth Fibonacci.','MEDIUM','5;6;7;8;3;4;2;1','5;8;13;21;2;3;1;1','5;7','5;13','Fibonacci'),(33,'Check if number is prime.','MEDIUM','2;3;4;9;11;15;17;21','true;true;false;false;true;false;true;false','5;10','true;false','Prime Check'),(34,'Find GCD of two numbers.','MEDIUM','10 5;15 10;100 10;9 3;8 4;7 3;6 2;25 5','5;5;10;3;4;1;2;5','12 18;20 30','6;10','GCD'),(35,'Find LCM of two numbers.','MEDIUM','2 3;5 10;6 8;7 3;9 6;10 20;8 12;15 5','6;10;24;21;18;20;24;15','4 6;5 10','12;10','LCM'),(36,'Count digits in number.','MEDIUM','1;22;333;4444;55555;666666;7777777;88888888','1;2;3;4;5;6;7;8','12345;987','5;3','Count Digits'),(37,'Reverse digits.','MEDIUM','123;456;789;321;654;987;111;222','321;654;987;123;456;789;111;222','123;456','321;654','Reverse Number'),(38,'Sum digits of number.','MEDIUM','123;456;789;321;654;987;111;222','6;15;24;6;15;24;3;6','123;456','6;15','Sum of Digits'),(39,'Check Armstrong number.','MEDIUM','153;370;371;407;100;200;300;111','true;true;true;true;false;false;false;false','153;123','true;false','Check Armstrong'),(40,'Swap two numbers.','MEDIUM','1 2;10 20;5 6;7 8;9 10;11 12;13 14;15 16','2 1;20 10;6 5;8 7;10 9;12 11;14 13;16 15','2 3;10 20','3 2;20 10','Swap Numbers'),(41,'Find maximum subarray sum.','HARD','5\n1 2 3 4 5;4\n-1 -2 -3 -4;6\n-2 1 -3 4 -1 2;3\n5 -1 5;4\n1 -2 3 4;5\n-5 -4 -3 -2 -1;4\n2 3 -2 4;3\n-1 2 3','15;-1;5;9;7;-1;7;5','5\n-2 1 -3 4 -1;4\n1 2 3 4','4;10','Max Subarray Sum'),(42,'Find longest substring length.','HARD','pwwkew;abcdef;abba;dvdf;anviaj;abcdeafgh;zzzz;abcbde','3;6;2;3;5;8;1;4','abcabcbb;bbbbb','3;1','Longest Substring Without Repeat'),(43,'Check if pair exists.','HARD','5\n1 2 3 4 5\n10;4\n2 4 6 8\n14;3\n1 2 3\n5;4\n5 6 7 8\n20;3\n2 3 4\n10;5\n1 1 1 1 1\n2;4\n3 3 3 3\n6;3\n7 8 9\n17','true;true;true;false;false;true;true;true','5\n1 2 3 4 5\n6;3\n1 2 3\n7','true;false','Pair Sum'),(44,'Sum from L to R.','HARD','5\n1 2 3 4 5\n0 4;4\n2 4 6 8\n1 2;3\n5 5 5\n0 2;6\n1 1 1 1 1 1\n2 5;4\n3 6 9 12\n1 3;5\n1 3 5 7 9\n2 4;3\n2 4 6\n0 1;4\n8 6 4 2\n1 3','15;10;15;4;27;15;6;12','5\n1 2 3 4 5\n1 3;4\n2 4 6 8\n1 2','9;10','Range Sum'),(45,'Product of array except self.','HARD','4\n1 2 3 4;3\n1 2 3;5\n1 1 1 1 1;4\n5 6 7 8;3\n2 3 4;4\n9 8 7 6;3\n3 3 3;2\n10 20','24 12 8 6;6 3 2;1 1 1 1 1;336 280 240 210;12 8 6;336 378 432 504;9 9 9;20 10','4\n1 2 3 4;3\n1 2 3','24 12 8 6;6 3 2','Product Except Self'),(46,'Check valid parentheses.','HARD','()[]{};([{}]);(];([)];((()));[{}];((;{[()]}','true;true;false;false;true;true;false;true','();(]','true;false','Valid Parentheses'),(47,'Max sum of window.','HARD','5\n1 2 3 4 5\n2;4\n2 3 4 5\n2;6\n1 1 1 1 1 1\n3;5\n5 4 3 2 1\n2;4\n1 2 3 4\n1;5\n2 2 2 2 2\n2;3\n3 6 9\n2;4\n1 3 5 7\n3','9;9;3;9;4;4;15;15','5\n1 2 3 4 5\n3;4\n2 3 4 5\n2','12;9','Sliding Window Sum'),(48,'Find first unique char.','HARD','hello;swiss;programming;aabbccdde;abcd;zzxy;testcase;aabbcd','h;w;p;-1;a;x;t;c','aabbc;hello','c;h','First Unique Character'),(49,'Rotate array by k.','HARD','4\n1 2 3 4\n1;5\n5 6 7 8 9\n3;3\n1 2 3\n2;6\n1 2 3 4 5 6\n3;4\n9 8 7 6\n2;5\n2 4 6 8 10\n1;3\n7 8 9\n1;4\n10 20 30 40\n3','4 1 2 3;7 8 9 5 6;2 3 1;4 5 6 1 2 3;7 6 9 8;10 2 4 6 8;9 7 8;20 30 40 10','5\n1 2 3 4 5\n2;3\n1 2 3\n1','4 5 1 2 3;3 1 2','Rotate Array'),(50,'Find majority element.','HARD','5\n2 2 2 2 2;4\n3 3 3 3;3\n1 2 3;6\n2 2 2 2 3 3;4\n7 7 7 7;5\n9 9 9 1 2;4\n8 8 8 8','2;3;-1;2;7;9;8','5\n2 2 1 2 2;3\n1 2 3','2;-1','Majority Element');
/*!40000 ALTER TABLE `problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `actual_output` text,
  `code` text,
  `error_message` text,
  `expected_output` text,
  `failed_test_case` int DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  `problem_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj5kbdqokftgx992cx24x3s583` (`problem_id`),
  KEY `FK760bgu69957phd7hax608jdms` (`user_id`),
  CONSTRAINT `FK760bgu69957phd7hax608jdms` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj5kbdqokftgx992cx24x3s583` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submissions`
--

LOCK TABLES `submissions` WRITE;
/*!40000 ALTER TABLE `submissions` DISABLE KEYS */;
INSERT INTO `submissions` VALUES (1,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:05.490178',1,1),(2,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:10.217744',1,1),(3,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:13.517248',1,1),(4,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:19.844169',1,1),(5,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:19.887711',1,1),(6,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:24.747384',1,1),(7,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}','COMPILATION_ERROR',NULL,1,'JAVA','COMPILATION_ERROR','2026-05-24 17:16:26.052893',1,1),(8,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:27.928932',1,1),(9,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:31.616210',1,1),(10,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:41.509986',1,1),(11,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:41.550172',1,1),(12,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:44.715422',1,1),(13,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:48.033690',1,1),(14,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:48.069692',1,1),(15,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b)\n    }\n}','COMPILATION_ERROR',NULL,1,'JAVA','COMPILATION_ERROR','2026-05-24 17:16:50.074610',1,1),(16,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b)\n    }\n}','COMPILATION_ERROR',NULL,1,'JAVA','COMPILATION_ERROR','2026-05-24 17:16:50.092133',1,1),(17,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:16:52.508774',1,1),(18,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n        System.out.println(a + b)\n    }\n}','COMPILATION_ERROR',NULL,1,'JAVA','COMPILATION_ERROR','2026-05-24 17:16:54.774054',1,1),(19,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 17:26:18.930114',1,1),(20,NULL,'class Main {\n    public static void main(String[] args) {\n        java.util.Scanner sc = new java.util.Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 18:10:25.317124',1,1),(21,'Hello World','class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello World\");\n    }\n}','WRONG_ANSWER','5',1,'JAVA','WRONG_ANSWER','2026-05-24 19:02:28.754015',1,1),(22,'','import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}','WRONG_ANSWER','5;30',1,'JAVA','WRONG_ANSWER','2026-05-24 20:47:50.652427',1,1),(23,'2','import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int sum = 0;\n\n        while (sc.hasNextInt()) {\n            sum += sc.nextInt();\n        }\n\n        System.out.println(sum);\n    }\n}','WRONG_ANSWER','5;30',1,'JAVA','WRONG_ANSWER','2026-05-24 20:58:41.509452',1,1),(24,'2','import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int sum = 0;\n\n        while (sc.hasNextInt()) {\n            sum += sc.nextInt();\n        }\n\n        System.out.println(sum);\n    }\n}','WRONG_ANSWER','5;30',1,'JAVA','WRONG_ANSWER','2026-05-24 20:59:59.623550',1,1),(25,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int sum = 0;\n\n        while (sc.hasNextInt()) {\n            sum += sc.nextInt();\n        }\n\n        System.out.println(sum);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:05:15.192516',1,1),(26,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int sum = 0;\n\n        while (sc.hasNextInt()) {\n            sum += sc.nextInt();\n        }\n\n        System.out.println(sum);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:09:53.403840',1,1),(27,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int sum = 0;\n\n        while (sc.hasNextInt()) {\n            sum += sc.nextInt();\n        }\n\n        System.out.println(sum)\n    }\n}','COMPILATION_ERROR',NULL,1,'JAVA','COMPILATION_ERROR','2026-05-24 21:10:16.075711',1,1),(28,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:43:00.019873',1,1),(29,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:47:27.841650',1,1),(30,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:58:40.417498',1,1),(31,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a - b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 21:59:10.491683',2,1),(32,NULL,'import java.util.*;\n\nclass Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        int a = sc.nextInt();\n        int b = sc.nextInt();\n\n        System.out.println(a + b);\n    }\n}',NULL,NULL,-1,'JAVA','ACCEPTED','2026-05-24 22:07:02.778646',1,1);
/*!40000 ALTER TABLE `submissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-05-24 16:32:22.649158','test@gmail.com','karthic','$2a$10$ZRe/wSBdGzzsXQug9T..eOeC79g1nHt5jlzMIUoAQL9eIYkzPVdRi'),(2,'2026-05-24 16:45:10.060749','test2@gmail.com','Karthi','$2a$10$Kw7oIaVeWc14YaGYfZjvaeQByTjtW7mMmEKU9pnQP8Cd.yYxO53Mi');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'codearena'
--

--
-- Dumping routines for database 'codearena'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-29 19:52:43
