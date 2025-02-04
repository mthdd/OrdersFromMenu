package com.cdr.orders.adapter

// OrdersAdapter.kt
class OrdersAdapter(
    private val orders: List<Order>,
    private val onItemClick: (Order) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
        holder.itemView.setOnClickListener { onItemClick(order) }
    }

    override fun getItemCount() = orders.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            itemView.apply {
                findViewById<TextView>(R.id.tvLocalId).text = order.localId
                findViewById<TextView>(R.id.tvType).text = when(order.type) {
                    OrderType.IN_HOUSE -> "За столом"
                    OrderType.TAKEAWAY -> "На вынос"
                    OrderType.DELIVERY -> "Доставка"
                }
                findViewById<TextView>(R.id.tvAmount).text =
                    "%.2f ₽".format(order.totalAmount)
                setBackgroundColor(order.status.colorResId)
            }
        }
    }
}