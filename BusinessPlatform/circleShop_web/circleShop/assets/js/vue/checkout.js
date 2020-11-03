new Vue({
    el: '#app',
    data() {
        return {
            selectGoods: []
        }
    },
    methods: {
        createOrder() {
            http.post('/api/order/createOrder', this.selectGoods).then(resp => {
                if (resp.data.code == 1 && resp.data.data.createOrders > 0) {
                    location.href = './my-account.html'
                }
            })
            localStorage.removeItem('selectGoods')
        }
    },
    created() {
        this.selectGoods = JSON.parse(goods)
        // localStorage.removeItem('checkOutGoods')
    },
    computed: {
        calculateTotal() {
            let total = 0;
            for (let i = 0; i < this.selectGoods.length; i++){
                let cart = this.selectGoods[i]
                total += ((cart.special == null ? cart.price : cart.special) * cart.amount)
            }
            return total.toFixed(2)
        }
    },
})