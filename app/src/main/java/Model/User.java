package Model;

public class User {
    String name, bloodgroup, id, email, idnumber, phonenumber,profilepictureurl,search,type;

    String city, state;
    int age, height;
    int weight;
    String gender;

    String hypertension, diabetes, anemia, anyOther;
    String showPhone;

    public User() {
    }

//    public User(String name, String bloodgroup, String id, String email, String idnumber, String phonenumber, String profilepictureurl, String search, String type) {
//        this.name = name;
//        this.bloodgroup = bloodgroup;
//        this.id = id;
//        this.email = email;
//        this.idnumber = idnumber;
//        this.phonenumber = phonenumber;
//        this.profilepictureurl = profilepictureurl;
//        this.search = search;
//        this.type = type;
//    }


    public User(String name, String bloodgroup, String id, String email, String idnumber, String phonenumber, String profilepictureurl, String search, String type,
                String city, String state, int age, int height, int weight, String gender, String hypertension, String diabetes, String anemia, String anyOther, String showPhone)
    {
        this.name = name;
        this.bloodgroup = bloodgroup;
        this.id = id;
        this.email = email;
        this.idnumber = idnumber;
        this.phonenumber = phonenumber;
        this.profilepictureurl = profilepictureurl;
        this.search = search;
        this.type = type;


        this.city = city;
        this.state = state;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.anemia = anemia;
        this.anyOther = anyOther;

        this.showPhone = showPhone;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getProfilepictureurl() {
        return profilepictureurl;
    }

    public void setProfilepictureurl(String profilepictureurl) {
        this.profilepictureurl = profilepictureurl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}


    public String getState() {return state;}
    public void setState(String state){this.state = state;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public int getHeight(){return height;}
    public void setHeight(int height){this.height = height;}

    public int getWeight(){return weight;}
    public void setWeight(int weight){this.weight = weight;}

    public String getGender(){return gender;}
    public void setGender(String gender){this.gender = gender;}

    public String getHypertension(){return hypertension;}
    public void setHypertension(String hypertension){this.hypertension = hypertension;}

    public String getDiabetes(){return diabetes;}
    public void setDiabetes(String diabetes){this.diabetes = diabetes;}

    public String getAnemia(){return anemia;}
    public void setAnemia(String anemia){this.anemia = anemia;}

    public String getAnyOther() {return anyOther;}
    public void setAnyOther(String anyOther){this.anyOther = anyOther;}

    public String getShowPhone(){return showPhone;}
    public void setShowPhone(String showPhone){this.showPhone = showPhone;}


}

