import accountApi from '../../api/index.js';


export default{
    state:{
        user:{},
        token:{},
    },
    getters:{
        user: state => state.user,
        token: state => state.token
    },
    actions:{
        fetchUser({commit}){
            accountApi.getUser(
              res => {
                console.log("res =" + res)
                commit('setUser', res.user)
                
              }
            )
        }
    },
    mutations:{
        setToken(state, token){
            state.token = token
        },
            
        setUser(state, user){
            state.user = user
        }
    }
}


