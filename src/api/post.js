import req from './req-wrapper'




const BE_URI = {
    POST: `/post`,
    POSTLIST: `/mypage`,

}

export default{
    Uploadpost(body, success) {
        req.post(BE_URI.POST, body ,success)
    }

}