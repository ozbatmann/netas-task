package com.netas.project.view;

import com.netas.project.model.Student;
import com.netas.project.service.StudentService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ManagedBean(name="studentView")
@ViewScoped
public class StudentView implements Serializable {

    @Autowired
    StudentService studentService;

    private String name;

    private String surname;

    private String phoneNumber;

    private String city;

    private String district;

    private String description;

    private Student selectedStudent;

    private UploadedFile file;

    private Map<String,Map<String,String>> cityDistrictMap = new HashMap<String, Map<String,String>>();
    private Map<String,String> cityMap = new HashMap<String,String>();
    private Map<String,String> districtMap = new HashMap<String,String>();

    private List<Student> studentList;

    // Maps are filled when the application starts.
    @PostConstruct
    public void postInit(){
        cityMap  = new HashMap<String, String>();
        cityMap.put("Ankara", "Ankara");
        cityMap.put("Konya", "Konya");

        Map<String,String> map = new HashMap<String, String>();
        map.put("Çankaya", "Çankaya");
        map.put("Yenimahalle", "Yenimahalle");
        cityDistrictMap.put("Ankara", map);

        map = new HashMap<String, String>();
        map.put("Meram", "Meram");
        map.put("Selçuklu", "Selçuklu");
        cityDistrictMap.put("Konya", map);

        getStudents();
        reset();

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Map<String, Map<String, String>> getCityDistrictMap() {
        return cityDistrictMap;
    }

    public void setCityDistrictMap(Map<String, Map<String, String>> cityDistrictMap) {
        this.cityDistrictMap = cityDistrictMap;
    }

    public Map<String, String> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, String> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, String> getDistrictMap() {
        return districtMap;
    }

    public void getStudents() {
        studentList = studentService.getStudentList();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public void setDistrictMap(Map<String, String> districtMap) {
        this.districtMap = districtMap;
    }

    // City change event
    public void onCityChange(){
        if(city !=null && !city.equals(""))
            districtMap = cityDistrictMap.get(city);
        else
            districtMap = new HashMap<String, String>();
    }
    public void onCityC(String c){
        if(c !=null && !c.equals(""))
            districtMap = cityDistrictMap.get(c);
        else
            districtMap = new HashMap<String, String>();
    }

    // Add - Edit - Delete
    public void addStudent(){
        studentService.addStudent(name,surname,phoneNumber,city,district,description,file);
        getStudents();
        reset();
    }

    public void onRowEdit(RowEditEvent<Student> event) {
       studentService.updateStudent(event.getObject());
        getStudents();
    }

    public String delete(int id) {
       studentService.deleteStudent(id);
        getStudents();
       return "studentsList.xhtml?faces-redirect=true";
    }

    // Reset function
    public String reset() {
       this.setName(null);
       this.setSurname(null);
       this.setPhoneNumber(null);
       this.setCity(null);
       this.setDistrict(null);
       this.setDescription(null);

       return "createStudent.xhtml?faces-redirect=true";
    }

    // Handle file upload event
    public void handleFileUpload(FileUploadEvent event) {
       setFile(event.getFile());
    }

    // Update file
    public void updateFile(){
        studentService.updateFile(selectedStudent,file);
    }
}
