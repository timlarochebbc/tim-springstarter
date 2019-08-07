# Hello Stuart

@Service is a special type of @Component. The annotations mark the class as Beans for Spring IOC to pick it up and put it into the application context and use it. From what I've read, since we used @ComponentScan to find all of our Beans, Spring doesn't catch the @RestController (which is also a type of @Component that lets you use @RequestMapping) without us explicitly telling it that it is also @Service. 
