new Vue({
    el: '#app',
    data() {
        return {
            //所有的细节信息
            goodsDetails: [],
            //当前在页面渲染的细节信息
            goodsDetail: {},
            scopeShow: false,
            quantity: 1      
        }
    },
    methods: {
        addToCart() {
            http.post('/api/cart/addToCart', { gdId: this.goodsDetail.gdId, amount: this.quantity })
                .then(resp => {
                    if (resp.data.code == 2) {
                        let recentView = location.href
                        //localStorage基于浏览器的，是一个所有页面都可以访问的公共空间
                        localStorage.setItem('recentView', recentView)
                        location.href = './login.html'
                    } else {
                        alert(resp.data.message)
                    }
                })
        }
    },
    created() {
        http.get('/api/goods/findDetailById', { gId: gid }).then(resp => {
            this.goodsDetails = resp.data.data.goods
            this.goodsDetail = this.goodsDetails[0]
        })
    }
})