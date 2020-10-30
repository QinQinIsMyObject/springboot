new Vue({
    el: '#app',
    data() {
        return {
            carts: []
        }
    },
    methods: {
        deleteSingle(gdId, cart) {
            http.post('/api/cart/deleteByUIdAndGdId', [{ gdId }]).then(resp => {
                if (resp.data.code == 1) {
                    this.carts = this.carts.filter((c) => {
                        return c!=cart
                    })
                }
            })
        }
    },
    created() {
        http.get('/api/cart/selectByUId').then(resp => {
            if (resp.data.code == 2) {
                let recentView = location.href
                localStorage.setItem('recentView', recentView)
                location.href = './login.html'
            }
            this.carts = resp.data.data.carts
        })
    }
})