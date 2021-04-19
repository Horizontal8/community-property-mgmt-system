import axios from 'axios'

const EVENTS_REST_API_URL='http://localhost:8080/api/events';

class RoomBookingService {
    getEvents(){
        return axios.get(EVENTS_REST_API_URL);
    }

    createEvent(event){
        return axios.post(EVENTS_REST_API_URL, event);
    }

    updateEvent(event, eventId){
        return axios.put(EVENTS_REST_API_URL + '/' + eventId, event);
    }

    deleteEvent(eventId){
        return axios.delete(EVENTS_REST_API_URL + '/' + eventId);
    }
}

export default new RoomBookingService();