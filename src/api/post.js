import req from './req-wrapper'


const BE_URI = {
    POST:'/post',
    POSTLIST:'post',

}

export default{
    getUser(success) {
        console.log("1")
        req.get(BE_URI.USER, success)
    }

}