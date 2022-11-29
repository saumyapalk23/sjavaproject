package com.nighthawk.spring_portfolio.mvc.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject.Type.JsonType;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;    
    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    @Column(unique=false)
    private int height;

    @Column(unique=false)
    private int weight;

    @Column(name = "occupation")
    private String occupation;
    
    @Column(name = "BMI")
    private String BMI;


    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    


    // Constructor used when building object from an API
    public Person(String email, String password, String name, int height, String occupation, Date dob) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.occupation = occupation;
        this.height = height;
    }

    //more person attributes
    public double weight(){
        double one = this.height * this.height * 25;
        double weight = one/704;
        return weight;
    }     
    public int getBmi(){
        int bmi = (int)( 703 * this.weight / Math.pow(this.height, 2) );
        return bmi;
    }    


    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"name\": " + this.name + ", " + "\"height\": " + this.height + "\"dob\": " + this.dob + " ," + "\"occupation\": " + this.occupation + " ," + "\"weight\": " + this.weight() + " ," + "\"bmi\": " + this.getBmi() + ", " + "\"age\": "+this.getAge()+ " }" );
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public String getAgeToString(){
        return ("{ \"name\": " + this.name + " ," + "\"age\": " + this.getAge() + " }" );
    }


    public static void main(String[] args){
        
        Person Saumya = new Person();
        System.out.println(Saumya);

        LocalDate myDate = LocalDate.of(2006, 11, 21);
        Date date = Date.from(myDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Person allArgsPerson = new Person("saumyp3@gmail.com", "sPALk", "Saumya Palakodety", 53, "student", date);
        System.out.println(allArgsPerson.getAge());
        System.out.println(allArgsPerson);

        //more methods
        // System.out.println(allArgsPerson.toString());
    }
}



