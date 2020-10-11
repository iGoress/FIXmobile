package br.com.luisbocchini.primeiroapp

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import  kotlinx.android.synthetic.main.adapter_empresa.*

// define o construtor que recebe a lista de empresas e o evento de clique
class EmpresaAdapter (
    val empresas: List<Empresas>,
    val onClick: (Empresas) -> Unit): RecyclerView.Adapter<EmpresaAdapter.EmpresasViewHolder>() {

    // ViewHolder com os elemetos da tela
    class EmpresasViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_empresas)

        }

    }

    // Quantidade de empresas na lista

    override fun getItemCount() = this.empresas.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresasViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_empresa, parent, false)

        // retornar ViewHolder
        val holder = EmpresasViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: EmpresasViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto empresa
        val empresa = empresas[position]

        // atualizar dados de empresa

        holder.cardNome.text = empresa.nome
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(empresa.foto).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(empresa)}
    }
}