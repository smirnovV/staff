export async function request(url) {
    return fetch(url)
        .then(response => {
            if(response.status >= 200 && response.status < 300) {
                return response
            }
            else {
                let error = new Error(response.statusText)
                error.response = response
                throw error
            }
        })
        .then(response=>response.json())
        .catch(error => {
            console.log(error);
        });
}