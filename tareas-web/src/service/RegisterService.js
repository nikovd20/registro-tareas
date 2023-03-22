import axios from "axios";


export default class RegisterService{

    url = "http://localhost:8080/api"

    getTasks() {
        return axios.get(this.url);
    }

    save(task){
        return axios.post(this.url, task)
    }

    delete(id) {
        return axios.delete(this.url + "/" + id)
    }
}