new Vue({
    el: '#app',
    data() {
        return {
            carts: [],
            select: false,
            selectGoods: []
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
        },
        selectAll() {
            this.select = !this.select
            //使用jquery选中所有的checkbox标签
            $('.product-select-box').attr('checked', this.select)
        },
        checkOut() {
            if (this.selectGoods.length < 1) {
                alert('您还未选择任何商品！')
            } else {
                localStorage.setItem('checkOutGoods', JSON.stringify(this.selectGoods))
                location.href = './checkout.html'
            }
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