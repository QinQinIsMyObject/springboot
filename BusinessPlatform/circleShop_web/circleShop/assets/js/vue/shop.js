new Vue({
    el: '#app',
    data() {
        return {
            goodsList: {},
            // pageInfo: {
            //     hasNextPage: false,
            //     hasPreviousPage: false,
            //     nextPage: 0,
            //     prePage: 0,
            //     pageNum: 0,
            //     pages: 0,
            //     total: 0
            // }
        }
    },
    methods: {
        goodsPage(pageNum) {
            http.get('/api/goods/goodsList', { pageNum }).then(resp => {
                this.goodsList = resp.data.data.goodsList
            })
        },
        goodsDetails(gid) {
            location.href = './product-details.html?gid='+gid
        }
    },
    created() {
        http.get('/api/goods/goodsList').then(resp => {
            this.goodsList = resp.data.data.goodsList
        })
    },
    computed: {
        pages() {
            return new Array(this.goodsList.pages)
        }
    },
})