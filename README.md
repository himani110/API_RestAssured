# REST ASSURED API AUTOMATION

# TOOLS USED 
- REST Assured
- Test NG 
- Maven installed in your classpath
- Java JDK 15+
- Dummy /Public API automated - 
- 
# How to Execute the tests ?

# Basic and Important Concepts:

1. What are API's?
2. Importance of API Automation
3. Client and Server snippet Background
3. Automation Framework - and which one I have used for Automation here 
4. Serialization and Deserialization
   Serialization is the process of converting the object of a class into a byte stream and deserialization is vice versa.
   Data is exchanged between client and server in the form of JSON/XML, so serialization is the process of converting Java objects (be it hashmap or POJO classes) into JSON and 
   deserialization is the reverse process of mapping JSON to object.
   Rest Assured can use Jackson/GSON library for the same. We are using Jackson in our framework.
5. Why and What is creating POJO Class ?
   Plain old Java Object (POJO) is a class which used for object representation of structured data(JSON). 
   This class contains all the keys as nodes and provides getters and setters to add the abstraction layer.
   Why use POJO?
    - For better readability 
    - Reusability
    - Easy access to data 
    - Type Safety
