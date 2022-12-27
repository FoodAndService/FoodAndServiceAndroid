package com.foodandservice.presentation.ui.restaurant_reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.foodandservice.R
import com.foodandservice.databinding.FragmentRestaurantReviewsBinding
import com.foodandservice.domain.model.RestaurantReview
import com.foodandservice.presentation.ui.adapter.RestaurantReviewAdapter
import com.foodandservice.util.RecyclerViewItemDecoration
import java.time.LocalDateTime

class RestaurantReviewsFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantReviewsBinding
    private lateinit var restaurantReviewAdapter: RestaurantReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_restaurant_reviews, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapters()

        binding.apply {
            rvRestaurantReview.adapter = restaurantReviewAdapter
            rvRestaurantReview.addItemDecoration(RecyclerViewItemDecoration(topMargin = 32))

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnAddReview.setOnClickListener {
                navigateToAddReview()
            }
        }
    }

    private fun navigateToAddReview() {
        findNavController().navigate(R.id.action_restaurantReviewsFragment_to_reviewCreateFragment)
    }

    private fun setAdapters() {
        val reviews = listOf(
            RestaurantReview(
                id = "1",
                clientName = "Eugenio Chebotarev",
                rating = 3f,
                description = "El servicio en este restaurante es excelente. Los meseros son muy atentos y siempre están dispuestos a ayudar. Además, la comida es increíblemente deliciosa. Mi plato favorito es el filete con ensalada y papas fritas. ¡Definitivamente volveré!",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(3)
            ), RestaurantReview(
                id = "2",
                clientName = "Diego Hermoso",
                rating = 4f,
                description = "¡Qué experiencia increíble! La decoración del restaurante es hermosa y el ambiente es muy agradable. La comida es increíblemente fresca y siempre se sirve caliente. Los precios son razonables y el servicio es excelente.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(4)
            ), RestaurantReview(
                id = "3",
                clientName = "David Gallardo Torres",
                rating = 3.5f,
                description = "Este es sin duda el mejor restaurante de la ciudad. La comida es increíblemente sabrosa y siempre se sirve caliente y fresca. Los meseros son muy amables y siempre están dispuestos a ayudar.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(5)
            ), RestaurantReview(
                id = "4",
                clientName = "Lorenzo Gómez Ríos",
                rating = 2.5f,
                description = "Muchas gracias por la reseña, tendremos en cuenta su punto.",
                isOwnerAnswer = true,
                date = LocalDateTime.now().minusDays(7)
            ), RestaurantReview(
                id = "5",
                clientName = "Anatoly Chebotarev",
                rating = 4f,
                description = "Mi esposo y yo siempre disfrutamos de nuestras visitas a este restaurante. La comida es siempre deliciosa y la atención al cliente es excelente.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(13)
            ), RestaurantReview(
                id = "6",
                clientName = "Sandra López Romarís",
                rating = 5f,
                description = "Este es sin duda mi restaurante favorito en la ciudad. La comida es increíblemente deliciosa y siempre se sirve caliente y fresca.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(15)
            ), RestaurantReview(
                id = "7",
                clientName = "Adrián Pintor",
                rating = 4.5f,
                description = "Mi familia y yo hemos estado yendo a este restaurante durante años y siempre tenemos una experiencia increíble. La comida es deliciosa y el servicio es excelente. Los meseros siempre están dispuestos a ayudar y asegurarse de que tengamos una excelente experiencia.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(18)
            ), RestaurantReview(
                id = "8",
                clientName = "Daniel Ortega",
                rating = 1f,
                description = "Mi esposo y yo visitamos este restaurante por primera vez la semana pasada y quedamos completamente sorprendidos por lo deliciosa que era la comida. Los meseros fueron muy amables y siempre estuvieron dispuestos a ayudar.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(21)
            ), RestaurantReview(
                id = "9",
                clientName = "Javier Mohedas",
                rating = 2.5f,
                description = "Este es sin duda uno de mis restaurantes favoritos en la ciudad. La comida siempre es deliciosa y el servicio es excelente.",
                isOwnerAnswer = false,
                date = LocalDateTime.now().minusDays(22)
            ), RestaurantReview(
                id = "10",
                clientName = "Anzhelika Ch",
                rating = 3f,
                description = "Muchas gracias por esas bonitas palabras!",
                isOwnerAnswer = true,
                date = LocalDateTime.now().minusDays(30)
            )
        )

        restaurantReviewAdapter = RestaurantReviewAdapter()
        restaurantReviewAdapter.submitList(reviews)
    }
}