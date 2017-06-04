package com.ticktech.booksgram.model;

import com.ticktech.booksgram.parser.BooksJsonParser;

import java.util.ArrayList;

/**
 * Created by Taha on 18/03/2016.
 */
public class BookDatasource {

    public ArrayList<Books> getList() {

        BooksJsonParser quotesParser = new BooksJsonParser();
        return quotesParser.getParsedQuotes();


//        ArrayList<Books> itemList = new ArrayList<Books>();
//
//        Books item1 = new Books();
//        item1.setBook_id(1);
//        item1.setBook_name("1. Go Programming");
//        item1.setBook_publisher("by Alan A. A. Donovan.");
//        item1.setBook_rating(5);
//        item1.setBook_logo("book_go");
//        item1.setBook_price("860 PKR");
//        item1.setBook_description("The Go Programming Language is the authoritative resource for any programmer who wants to learn Go. It shows how to write clear and idiomatic Go to solve real-world problems. The book does not assume prior knowledge of Go nor experience with any specific language, so you’ll find it accessible whether you’re most comfortable with JavaScript, Ruby, Python, Java, or C++.");
//        itemList.add(item1);
//
//        Books item2 = new Books();
//        item2.setBook_id(2);
//        item2.setBook_name("2. Arduino Freak");
//        item2.setBook_publisher("by Simon Monk.");
//        item2.setBook_rating(4);
//        item2.setBook_logo("book_arduino");
//        item2.setBook_price("780 PKR");
//        item2.setBook_description("Clear, easy-to-follow examples show you how to program Arduino with ease! \"Programming Arduino: Getting Started with Sketches\" helps you understand the software side of Arduino and explains how to write well-crafted Sketches (the name given to Arduino programs) using the C language of Arduino. This practical guide offers an unintimidating, concise approach for non-programmers that will get you up and running right away");
//        itemList.add(item2);
//
//        Books item3 = new Books();
//        item3.setBook_id(3);
//        item3.setBook_name("3. Data Sciences");
//        item3.setBook_publisher("by Joel Grus.");
//        item3.setBook_rating(3);
//        item3.setBook_logo("book_datascience");
//        item3.setBook_price("2,200 PKR");
//        item3.setBook_description("Data science libraries, frameworks, modules, and toolkits are great for doing data science, but they’re also a good way to dive into the discipline without actually understanding data science. In this book, you’ll learn how many of the most fundamental data science tools and algorithms work by implementing them from scratch.");
//        itemList.add(item3);
//
//        Books item4 = new Books();
//        item4.setBook_id(4);
//        item4.setBook_name("4. The Hackers");
//        item4.setBook_publisher("by Peter Kim.");
//        item4.setBook_rating(4);
//        item4.setBook_logo("book_hacker");
//        item4.setBook_price("2.300 PKR");
//        item4.setBook_description("Just as a professional athlete doesn’t show up without a solid game plan, ethical hackers, IT professionals, and security researchers should not be unprepared, either. The Hacker Playbook provides them their own game plans. Written by a longtime security professional and CEO of Secure Planet, LLC, this step-by-step guide to the GAME of penetration hacking features hands-on examples and helpful advice from the top of the field.");
//        itemList.add(item4);
//
//        Books item5 = new Books();
//        item5.setBook_id(5);
//        item5.setBook_name("5. HTML & CSS");
//        item5.setBook_publisher("by Jon Duckett.");
//        item5.setBook_rating(5);
//        item5.setBook_logo("book_html");
//        item5.setBook_price("1,720 PKR");
//        item5.setBook_description("A full-color introduction to the basics of HTML and CSS from the publishers of Wrox! Every day, more and more people want to learn some HTML and CSS. Joining the professional web designers and programmers are new audiences who need to know a little bit of code at work (update a content management system or e-commerce store) and those who want to make their personal blogs more attractive.");
//        itemList.add(item5);
//
//        Books item6 = new Books();
//        item6.setBook_id(6);
//        item6.setBook_name("6. C++");
//        item6.setBook_publisher("by W.B Ean.");
//        item6.setBook_rating(3);
//        item6.setBook_logo("book_cplusplus");
//        item6.setBook_price("800 PKR");
//        item6.setBook_description("Have you always wanted to learn a computer programming but didn’t have the time? Or it looked to difficult to learn? Maybe you know C++ but you want to learn a little more faster? \n" +
//                "\n" +
//                "Then this book is for you. You no longer have to waste your time and money learning C++ from lengthy books, expensive online courses or complicated C++ tutorials.");
//        itemList.add(item6);
//
//        Books item7 = new Books();
//        item7.setBook_id(7);
//        item7.setBook_name("7. Python");
//        item7.setBook_publisher("by James Patterson.");
//        item7.setBook_rating(5);
//        item7.setBook_logo("book_python");
//        item7.setBook_price("300 PKR");
//        item7.setBook_description("Every programmer, programmer-wannabe, or person with just the slimmest brush with programming principles has heard of Python. It is a “dynamic” programming language, which has become one of the most popular of its kind, with Perl, PHP, and Ruby also contending for the roost. Just like any of these or other programming languages, Python is a unique and powerful language. Nowadays, it appears virtually anywhere—from scalable web servers that run uninterrupted ‘round the clock, to throw-away scripts that only see a few seconds of “daylight”. It can be used for both database and GUI programming, as well as both server-side and client-side programming. It can be used by first-time coders just stepping away from the basics, as well as by skilled developers in mission-critical settings. It is certainly one of the most versatile of its lot. ");
//        itemList.add(item7);
//
//        Books item8 = new Books();
//        item8.setBook_id(8);
//        item8.setBook_name("8. Photoshop");
//        item8.setBook_publisher("by Barbara.");
//        item8.setBook_rating(3);
//        item8.setBook_logo("book_photoshop");
//        item8.setBook_price("1,570 PKR");
//        item8.setBook_description("The bestselling book on Photoshop Elements--now in a new edition\n" +
//                "Getting great photos is easier than you think--it just takes some know-how and a tool like Adobe's Photoshop Elements. \"Photoshop Elements 14 For Dummies\" is a fun and easy-to-follow guide for photographers and photo enthusiasts who want to make their snapshots picture perfect. In no time, you'll tackle the basics of Photoshop Elements and find out how to execute hundreds of tasks, like using special effects and drawing tools, working with layers, improving your color and clarity, and so much more.");
//        itemList.add(item8);
//
//        Books item9 = new Books();
//        item9.setBook_id(9);
//        item9.setBook_name("9. Java Dev");
//        item9.setBook_publisher("by Adnan Aziz.");
//        item9.setBook_rating(2);
//        item9.setBook_logo("book_java");
//        item9.setBook_price("1,000 PKR");
//        item9.setBook_description("The sampler should give you a very good idea of the quality and style of our book. In particular, be sure you are comfortable with the level and with our Java coding style.\n" +
//                "\n" +
//                "Complete programs are available at epibook.github.io.\n" +
//                "\n" +
//                "Since different candidates have different time constraints, EPI includes a study guide with several scenarios, ranging from weekend Hackathon to semester long preparation with a recommended a subset of problems for each scenario.");
//        itemList.add(item9);
//
//        Books item10 = new Books();
//        item10.setBook_id(10);
//        item10.setBook_name("10. Hadoop");
//        item10.setBook_publisher("by Tom White.");
//        item10.setBook_rating(5);
//        item10.setBook_logo("book_hadoop");
//        item10.setBook_price("2,200 PKR");
//        item10.setBook_description("Get ready to unlock the power of your data. With the fourth edition of this comprehensive guide, you’ll learn how to build and maintain reliable, scalable, distributed systems with Apache Hadoop. This book is ideal for programmers looking to analyze datasets of any size, and for administrators who want to set up and run Hadoop clusters.");
//        itemList.add(item10);
//
//        return itemList;
    }
}
