new Vue({
    el: '#app',
    data() {
        return {
            interval: 120,
            verifyCode: '',
            confirmPwd: '',
            user: {
                uName: '',
                pwd: '',
                email: ''
            }
        }
    },
    methods: {
        sendVerify() {
            //进行邮箱格式验证
            if (this.interval == 120) {
                http.post('/api/mail/sendVerify', { email: this.user.email }).then(resp => {
                    console.log(resp)
                    if (resp.data.code == 1) {
                        let timer = setInterval(() => {
                            if (this.interval > 0) {
                                this.interval --
                            } else {
                                this.interval = 120
                                clearInterval(timer)
                            }
                        }, 1000)
                    } else {
                        alert(resp.data.message)
                    }
                })
            }
        },
        register() {
            //验证各种信息是否正确
            let data = new FormData()
            data.append('uName', this.user.uName)
            data.append('pwd', this.user.pwd)
            data.append('email', this.user.email)
            data.append('verifyCode', this.verifyCode)
            http.post('/api/user/register', data, { 'Content-Type': 'x-www-form-urlencoded;charset=utf-8'}).then(resp => {
                if (resp.data.code == 1) {
                    location.href = './login.html'
                } else {
                    alert(resp.data.message)
                }
            })
        }
    },
})