const BASE_URL = `http://localhost:8082`;
const SIGN_UP = `api/users`
const LOGIN = `api/users/login`

const fetchOptions = (body: any) => {
    return {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: body

    };
};


export default {
    BASE_URL,
    fetchOptions,
    SIGN_UP,
    LOGIN
};