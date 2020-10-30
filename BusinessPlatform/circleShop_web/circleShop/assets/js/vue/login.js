new Vue({
    el: '#app',
    data() {
        return {
            user: {
                username: '',
                password: ''
            },
            showPassword: false,
            usernameCorrect: true,
            passwordCorrect: true,
        }
    },
    methods: {
        regexCheckUsername() {
            this.usernameCorrect = checkUsername(this.user.username)
        },
        regexCheckPassword() {
            this.passwordCorrect = this.user.password !== ''
        },
        login() {
            this.regexCheckUsername()
            this.regexCheckPassword()
            //用户名和密码完全符合格式才能请求
            if (this.usernameCorrect && this.passwordCorrect) {
                //因为spring-security的登录接口要求传入 x-www-form-urlencoded 类型，所以需要使用FormData对象
                let data = new FormData()
                data.append('username', this.user.username)
                data.append('password', this.user.password)
                http.post('/api/user/login', data, { 'Content-Type': 'x-www-form-urlencoded;charset=utf-8' })
                    .then(resp => {
                        let recentView = localStorage.getItem('recentView')
                        if (recentView == undefined || recentView == '') {
                            location.href = './index.html'
                        } else {
                            localStorage.removeItem('recentView')
                            location.href = recentView
                        }
                    })
            }
        }
    },
})