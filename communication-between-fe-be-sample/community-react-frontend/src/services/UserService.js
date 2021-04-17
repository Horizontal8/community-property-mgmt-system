import axios from 'axios'

const USERS_REST_API_URL='http://localhost:8081/api/residents';

class UserService {
    getUsers(){
        return axios.get(USERS_REST_API_URL);
    }

    createUser(user){
        return axios.post(USERS_REST_API_URL, user);
    }

    getUserById(residentId){
        return axios.get(USERS_REST_API_URL + '/' + residentId);
    }

    updateUser(resident, residentId){
        return axios.put(USERS_REST_API_URL + '/' + residentId, resident);
    }
}

export default new UserService();