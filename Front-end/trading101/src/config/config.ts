const BASE_URL = `http://localhost:8082/api/users`;
const LOGIN = `login`
const  AUTHORIZATION_HEADER = "Authorization";
const  USERID = "UserID";
const acceptType = "application/json";

const fetchOptionsPost = (body: any) => {
    return {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": acceptType
        },
        body: body

    };
};

const fetchOptionsGet = (authorization: any) => {
    return {
        method: "GET",
        headers: {
            "Authorization": authorization,
            "Accept": acceptType
        },

    };
}

export default {
    BASE_URL,
    fetchOptionsPost,
    fetchOptionsGet,
    AUTHORIZATION_HEADER,
    USERID,
    LOGIN
};