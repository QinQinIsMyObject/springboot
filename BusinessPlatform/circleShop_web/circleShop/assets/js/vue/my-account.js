new Vue({
    el: '#app',
    data() {
        return {
            orders: []
        }
    },
    methods: {
        
    },
    created() {
        http.get('/api/order/getOrders').then(resp => {
            if (resp.data.code == 2 || resp.data.code == 0) {
                localStorage.setItem('recentView', './my-account.html')
                location.href = './login.html'
            } else {
                this.orders = resp.data.data.orders
            }
        }).catch(e => {
            localStorage.setItem('recentView', './my-account.html')
            location.href = './login.html'
        })
    },
})