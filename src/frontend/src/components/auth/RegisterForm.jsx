import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { useForm } from 'react-hook-form';

export const RegisterForm = ({ title, onSubmit, onGoToLogin }) => {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div className="flex align-items-center justify-content-center">
        <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
          <div className="text-center mb-5">
            <div className="text-900 text-3xl font-medium mb-3">{title}</div>
            <span className="text-600 font-medium line-height-3">
              Already have an account?
            </span>
            <a
              onClick={onGoToLogin}
              className="font-medium no-underline ml-2 text-blue-500 cursor-pointer">
              Sign in!
            </a>
          </div>

          <div>
            <label htmlFor="email" className="block text-900 font-medium mb-2">
              Name
            </label>
            <InputText
              id="name"
              type="text"
              placeholder="Nicolás C. Ibarra"
              className="w-full mb-3"
              {...register('name', { required: true })}
            />
            {errors.name && (
              <p className="text-red-300 -mt-2">A name is required</p>
            )}

            <div>
              <label
                htmlFor="email"
                className="block text-900 font-medium mb-2">
                Email
              </label>
              <InputText
                id="email"
                type="text"
                placeholder="Email address"
                className="w-full mb-3"
                {...register('email', { required: true })}
              />
              {errors.email && (
                <p className="text-red-300 -mt-2">An email is required</p>
              )}

              <label
                htmlFor="password"
                className="block text-900 font-medium mb-2">
                Password
              </label>
              <InputText
                id="password"
                type="password"
                placeholder="Password"
                className="w-full mb-3"
                {...register('password', { required: true })}
              />
              {errors.password && (
                <p className="text-red-300 -mt-2">A password is required</p>
              )}

              <label
                htmlFor="repeat-password"
                className="block text-900 font-medium mb-2">
                Confirm password
              </label>
              <InputText
                id="password"
                type="password"
                placeholder="Confirm password"
                className="w-full mb-3"
                {...register('repeatPassword', {
                  required: true,
                  validate: (val) => {
                    if (watch('password') !== val) {
                      return 'Your passwords do no match';
                    }
                  },
                })}
              />
              {errors.repeatPassword && (
                <p className="text-red-300 -mt-2">
                  A password confirmation is required and must match the
                  password
                </p>
              )}

              <Button
                label="Sign In"
                type="submit"
                icon="pi pi-user"
                className="w-full"
              />
            </div>
          </div>
        </div>
      </div>
    </form>
  );
};
