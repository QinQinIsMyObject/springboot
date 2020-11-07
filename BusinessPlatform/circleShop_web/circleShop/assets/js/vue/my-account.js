let getstate = function (state){
    switch (state) {
        case 0:
            return '待支付'
        case 1:
            return '完成付款'
        case 2:
            return '完成订单'
        case 3:
            return '订单取消'
        case 4:
            return '其他状态'
    }
}


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
    filters: {
        getstate 
    }
})