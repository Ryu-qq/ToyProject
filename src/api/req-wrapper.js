import axios from "axios";
import store from '../store/index.js'
import handler from "./res-handler";


const BACKEND_DOMAINPORT ='http://localhost:8080'
const URI_PREPENDER = '/api/v1';
const wrap = (url) => `${BACKEND_DOMAINPORT}${URI_PREPENDER}${url}`;
const appendAuth = (config) =>{
    const token = store.getters.token
    if(token){
        if(!config) config = {headers: {} };
        if(!config.headers) config.headers ={};
        config.headers.Authorization = `Bearer ${store.getters.token}`;
    }
    return config;
};

export default{
    get(url, success, fail = err => err.response.data.message, config){
        axios.get(wrap(url), appendAuth(config))
        .then(handler.handle(success))
        .catch(fail)
    },
}